package controllers;

import play.*;
import play.data.*;
import play.mvc.*;

import models.*;
import views.html.*;

import java.util.*;

public class Application extends Controller {

    public static Result index() {
        return ok("This is test.");
    }

}
