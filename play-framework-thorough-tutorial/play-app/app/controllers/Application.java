package controllers;

import play.*;
import play.data.*;
import play.mvc.*;

import models.*;
import views.html.*;

import java.util.*;

public class Application extends Controller {

    public static class SampleForm {
        public String input;
        public int num;
        public String pass;
        public boolean check;
        public String radio;
        public String sel;
        public String area;
        public Date date;
    }

    public static Result index() {
        SampleForm sf = new SampleForm();
        sf.radio = "windows";
        sf.check = true;
        sf.input = "default value";
        sf.sel = "uk";
        Form<SampleForm> form = new Form(SampleForm.class).fill(sf);
        return ok(index.render("please set form.", form));
    }

    public static Result send() {
        Form<SampleForm> f = new Form(SampleForm.class).bindFromRequest();
        if (!f.hasErrors()) {
            SampleForm sf = f.get();
            String res = "value: ";
            res += "input=" + sf.input
                + ", num=" + sf.num
                + ", pass=" + sf.pass
                + ", check=" + sf.check
                + ", radio=" + sf.radio
                + ", sel=" + sf.sel
                + ", area=" + sf.area
                + ", date=" + sf.date;
            return ok(index.render(res, f));
        } else {
            return badRequest(index.render("ERROR", f));
        }
    }

}
