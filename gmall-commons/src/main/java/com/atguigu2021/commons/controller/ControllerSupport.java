//package com.atguigu2021.commons.controller;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.lang.reflect.ParameterizedType;
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Iterator;
//import java.util.List;
//import java.util.UUID;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipOutputStream;
//import javax.inject.Inject;
//import javax.servlet.http.HttpServletResponse;
//
//import com.atguigu2021.commons.module.Dictionary;
//import com.atguigu2021.commons.module.Response;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.WebDataBinder;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.InitBinder;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
///**
// * @program: gmall
// * @description:
// * @author: Zhao Yi
// * @create: 2021-12-06 16:44
// */
//public abstract class ControllerSupport<M, Q extends Query, S> {
//    private static final Logger logger = LoggerFactory.getLogger(ControllerSupport.class);
//    public static final String APPLICATION_ZIP = "application/x-zip-compressed;charset=utf-8";
//    public static final String OCTETS_STREAM = "octets/stream";
//    public static final String BODY_PAGE = "bodyPage";
//    public static final String TITLE = "title";
//    @Inject
//    protected Session session;
//    @Inject
//    protected S service;
//    protected String module;
//
//    public ControllerSupport() {
//    }
//
//    @InitBinder
//    protected void initBinder(WebDataBinder dataBinder) {
//        dataBinder.registerCustomEditor(Date.class, new DateEditor(this, "yyyy-MM-dd HH:mm:ss"));
//    }
//
//    @ResponseBody
//    @RequestMapping(
//            value = {""},
//            method = {RequestMethod.POST}
//    )
//    protected Response add(M model) {
//        try {
//            ((Addable)this.service).add(model);
//            return Response.build().success();
//        } catch (Exception var3) {
//            return this.onException(var3);
//        }
//    }
//
//    @ResponseBody
//    @RequestMapping(
//            value = {""},
//            method = {RequestMethod.PUT}
//    )
//    protected Response update(M model) {
//        try {
//            ((Updatable)this.service).update(model);
//            return Response.build().success();
//        } catch (Exception var3) {
//            return this.onException(var3);
//        }
//    }
//
//    @ResponseBody
//    @RequestMapping(
//            value = {"/{id}"},
//            method = {RequestMethod.DELETE}
//    )
//    protected Response delete(@PathVariable("id") long id) {
//        try {
//            M model = ((Identifiable)this.service).findById(id);
//            if (model != null) {
//                ((Deletable)this.service).delete(model);
//            }
//
//            return Response.build().success();
//        } catch (Exception var4) {
//            return this.onException(var4);
//        }
//    }
//
//    @ResponseBody
//    @RequestMapping(
//            value = {"/{id}"},
//            method = {RequestMethod.GET}
//    )
//    protected Response findById(@PathVariable("id") long id) {
//        M m = ((Identifiable)this.service).findById(id);
//        return Response.build(m).success();
//    }
//
//    @ResponseBody
//    @RequestMapping(
//            value = {"/search"},
//            method = {RequestMethod.POST}
//    )
//    protected PageResponse find(Q query, Pagination pagination) {
//        final QPageQuery pageQuery = new QPageQuery(pagination, this.buildQuery(query));
//        final PageResponse response = new PageResponse(pagination, query);
//        this.execute(response, (ModelAndView)null, new ControllerSupport.Executor() {
//            public void execute() throws Exception {
//                Page page = ((Pageable)ControllerSupport.this.service).findByQuery(pageQuery);
//                if (page != null) {
//                    response.setResult(page.getResult());
//                    response.setPagination(page.getPagination());
//                }
//
//            }
//        });
//        return response;
//    }
//
//    @ResponseBody
//    @RequestMapping(
//            value = {"/enable/{id}"},
//            method = {RequestMethod.POST}
//    )
//    protected Response enable(@PathVariable("id") long id) {
//        try {
//            M model = ((Identifiable)this.service).findById(id);
//            if (model != null) {
//                ((Lifeable)this.service).enable(model);
//            }
//
//            return Response.build().success();
//        } catch (Exception var4) {
//            return this.onException(var4);
//        }
//    }
//
//    @ResponseBody
//    @RequestMapping(
//            value = {"/disable/{id}"},
//            method = {RequestMethod.POST}
//    )
//    protected Response disable(@PathVariable("id") long id) {
//        try {
//            M model = ((Identifiable)this.service).findById(id);
//            if (model != null) {
//                ((Lifeable)this.service).disable(model);
//            }
//
//            return Response.build().success();
//        } catch (Exception var4) {
//            return this.onException(var4);
//        }
//    }
//
//    protected Q buildQuery(Q query) {
//        if (query != null && query instanceof QSession) {
//            QSession qSession = (QSession)query;
//            UserDetail userDetail = this.session.getUser();
//            if (userDetail != null && !userDetail.isAdmin()) {
//                qSession.setUserId(userDetail.getId());
//            }
//        }
//
//        return query;
//    }
//
//    protected Page<Dictionary> find(Q query, Pagination pagination, ControllerSupport.DictionaryConverter<M> converter) {
//        QPageQuery<Q> pageQuery = new QPageQuery(pagination, this.buildQuery(query));
//        Page<M> page = ((Pageable)this.service).findByQuery(pageQuery);
//        if (page != null && page.getResult() != null) {
//            List<Dictionary> dictionaries = new ArrayList(page.getResult().size());
//            Iterator var7 = page.getResult().iterator();
//
//            while(var7.hasNext()) {
//                M model = var7.next();
//                dictionaries.add(converter.convert(model));
//            }
//
//            return new Page(page.getPagination(), dictionaries);
//        } else {
//            return null;
//        }
//    }
//
//    protected List<Dictionary> find(Q query, ControllerSupport.DictionaryConverter<M> converter) {
//        List<M> datum = Datum.findAll((Pageable)this.service, this.buildQuery(query), 1000);
//        if (datum != null && !datum.isEmpty()) {
//            List<Dictionary> dictionaries = new ArrayList(datum.size());
//            Iterator var5 = datum.iterator();
//
//            while(var5.hasNext()) {
//                M model = var5.next();
//                dictionaries.add(converter.convert(model));
//            }
//
//            return dictionaries;
//        } else {
//            return null;
//        }
//    }
//
//    protected void compress(InputStream in, OutputStream out, String name) throws IOException {
//        ZipOutputStream zout = new ZipOutputStream(out);
//
//        try {
//            zout.putNextEntry(new ZipEntry(name));
//            byte[] buffer = new byte[4096];
//
//            int len;
//            while((len = in.read(buffer)) > 0) {
//                zout.write(buffer, 0, len);
//            }
//
//            zout.closeEntry();
//        } finally {
//            Closeables.close(zout);
//        }
//    }
//
//    protected void compress(byte[] buffer, OutputStream out, String name) throws IOException {
//        ByteArrayInputStream in = new ByteArrayInputStream(buffer);
//
//        try {
//            this.compress((InputStream)in, (OutputStream)out, name);
//        } finally {
//            Closeables.close(in);
//        }
//
//    }
//
//    protected byte[] compress(String content, String charset, String name) throws IOException {
//        byte[] buffer = content.getBytes(charset);
//        ByteArrayInputStream in = new ByteArrayInputStream(buffer);
//        ByteArrayOutputStream out = new ByteArrayOutputStream(buffer.length);
//
//        byte[] var7;
//        try {
//            this.compress((InputStream)in, (OutputStream)out, name);
//            var7 = out.toByteArray();
//        } finally {
//            Closeables.close(in);
//            Closeables.close(out);
//        }
//
//        return var7;
//    }
//
//    protected void download(HttpServletResponse response, String name, String contentType, InputStream in, long length) throws IOException {
//        response.reset();
//        response.addHeader("Content-Disposition", "attachment;filename=" + new String(name.getBytes("utf-8"), "iso8859-1"));
//        response.addHeader("Content-Length", String.valueOf(length));
//        response.setContentType(contentType);
//        OutputStream os = response.getOutputStream();
//        IOUtils.copy(in, os, length);
//        os.flush();
//    }
//
//    protected void download(HttpServletResponse response, String name, String contentType, File file) throws IOException {
//        if (file.exists()) {
//            FileInputStream fis = new FileInputStream(file);
//
//            try {
//                this.download(response, name, contentType, fis, file.length());
//            } finally {
//                Closeables.close(fis);
//            }
//
//        }
//    }
//
//    protected File randomFile(String root, String path, String extension) {
//        if (extension != null && !extension.isEmpty()) {
//            String fileName = UUID.randomUUID().toString() + extension;
//            String filePath = root;
//            if (root == null || root.isEmpty()) {
//                filePath = this.getClass().getClassLoader().getResource("/").getPath();
//            }
//
//            File file = new File(filePath);
//            if (path != null && !path.isEmpty()) {
//                file = new File(file, path);
//            }
//
//            if (!file.exists()) {
//                file.mkdirs();
//            }
//
//            return new File(file, fileName);
//        } else {
//            throw new IllegalArgumentException("extension can not be empty.");
//        }
//    }
//
//    protected String getModule() {
//        if (this.module == null) {
//            Type genType = this.getClass().getGenericSuperclass();
//            Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
//            this.module = ((Class)params[0]).getSimpleName().toLowerCase();
//        }
//
//        return this.module;
//    }
//
//    @ExceptionHandler
//    @ResponseBody
//    protected Response onException(Exception e) {
//        if (!(e instanceof BusinessException)) {
//            logger.error(e.getMessage(), e);
//            return e instanceof RepositoryException ? Response.build().fail("存储错误") : Response.build().fail(e);
//        } else {
//            return Response.build().fail(e.getMessage());
//        }
//    }
//
//    protected void fail(Exception e, Response response) {
//        this.fail(e, response, (ModelAndView)null);
//    }
//
//    protected void fail(Exception e, Response response, ModelAndView mv) {
//        if (e != null && !(e instanceof BusinessException)) {
//            logger.error(e.getMessage(), e);
//        }
//
//        if (e != null && response != null) {
//            response.fail(e.getMessage());
//        }
//
//        if (mv != null) {
//            mv.addObject("response", response);
//            mv.setViewName("error");
//        }
//
//    }
//
//    protected <T extends Response> T execute(T response, ControllerSupport.Executor executor) {
//        try {
//            executor.execute();
//            if (response != null) {
//                response.success();
//            }
//        } catch (Exception var4) {
//            this.fail(var4, response);
//        }
//
//        return response;
//    }
//
//    protected <T extends Response> ModelAndView execute(T response, ModelAndView view, ControllerSupport.Executor executor) {
//        try {
//            executor.execute();
//            if (response != null) {
//                response.success();
//            }
//        } catch (Exception var5) {
//            this.fail(var5, response, view);
//        }
//
//        return view;
//    }
//
//    public void setSession(Session session) {
//        this.session = session;
//    }
//
//    public void setService(S service) {
//        this.service = service;
//    }
//
//    public interface DictionaryConverter<M> {
//        Dictionary convert(M var1);
//    }
//
//    public interface Executor {
//        void execute() throws Exception;
//    }
//}
