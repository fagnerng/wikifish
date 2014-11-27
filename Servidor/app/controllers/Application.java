package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import models.Comment;
import models.Fish;
import models.User;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import static play.libs.Json.fromJson;
import static play.libs.Json.toJson;
import static play.mvc.BodyParser.Json;

public class Application extends Controller {

    public static Result index() {
        return redirect(routes.Application.fishs());
    }

    public static Result fishs() {
        return ok(toJson(Fish.FINDER.all()));
    }

    @BodyParser.Of(Json.class)
    public static Result newFish() {
        try {
            JsonNode json = request().body().asJson();
            Fish fishObject = fromJson(json, Fish.class);
            fishObject.save();
            return created("/fish/" + fishObject.getId());
        } catch (Exception e) {
            if (e instanceof UnrecognizedPropertyException) {
                return badRequest("this Json is not valid");
            } else {
                return internalServerError("Something happened and was not possible persist this fish");
            }
        }
    }

    @BodyParser.Of(Json.class)
    public static Result updateFish(long id) {
        try {
            JsonNode json = request().body().asJson();
            Fish fishObject = fromJson(json, Fish.class);
            if (fishObject.getId() != id) {
                return badRequest("You cannot set the fish id.");
            }
            fishObject.update();
            return ok();
        } catch (Exception e) {
            if (e instanceof UnrecognizedPropertyException) {
                return badRequest("this Json is not valid");
            } else {
                return internalServerError("Something happened and was not possible persist this fish");
            }
        }
    }

    public static Result comments(long id) {
        Fish fish = Fish.FINDER.byId(id);
        if (fish == null) {
            return notFound("There is no fish with the id: " + id);
        }
        return ok(toJson(fish.getComments()));
    }

    public static Result newComments(String email, long id) {
        try {
            JsonNode json = request().body().asJson();
            Comment commentObject = fromJson(json, Comment.class);
            Fish fish = Fish.FINDER.byId(id);

            if (fish == null) {
                return notFound("There is no fish with the id: " + id);
            }
            commentObject.setOwner(User.FINDER.byId(email));
            fish.addComment(commentObject);
            fish.update();
            return created("/fish/" + id + "/comment/" + commentObject.getId());
        } catch (Exception e) {
            if (e instanceof UnrecognizedPropertyException) {
                return badRequest("this Json is not valid");
            } else {
                return internalServerError("Something happened and was not possible persist this fish");
            }
        }
    }

    public static Result updateComments(long fishId, long commentId) {
        try {
            JsonNode json = request().body().asJson();
            Comment commentObject = fromJson(json, Comment.class);
            Fish fish = Fish.FINDER.byId(fishId);
            Comment comment = Comment.FINDER.byId(commentId);

            //TODO probably there is a better way to do this, I will refactor some day.
            if (fish == null) {
                return notFound("There is no fish with the id: " + fishId);
            }
            if (comment == null) {
                return notFound("There is no comment with the id: " + commentId);
            }
            if (!fish.getComments().contains(comment)) {
                return notFound("There is no comment with the id: " + commentId + " in the fish with the id: " + fishId);
            }

            comment.update();
            return ok();
        } catch (Exception e) {
            if (e instanceof UnrecognizedPropertyException) {
                return badRequest("this Json is not valid");
            } else {
                return internalServerError("Something happened and was not possible persist this fish");
            }
        }
    }

    public static Result getFish(long id) {
        Fish fish = Fish.FINDER.byId(id);
        if (fish == null) {
            return notFound("There is no fish with the id: " + id);
        }
        return ok(toJson(fish));
    }

    public static Result deleteFish(long id) {
        Fish fish = Fish.FINDER.byId(id);
        if (fish == null) {
            return notFound("There is no fish with the id: " + id);
        }
        fish.delete();
        return ok();
    }

    public static Result deleteComment(long fishId, long commentId) {
        Comment commentObject = Comment.FINDER.byId(commentId);
        Fish fish = Fish.FINDER.byId(fishId);
        Comment comment = Comment.FINDER.byId(commentId);

        //TODO probably there is a better way to do this, I will refactor some day.
        if (fish == null) {
            return notFound("There is no fish with the id: " + fishId);
        }
        if (comment == null) {
            return notFound("There is no comment with the id: " + commentId);
        }
        if (!fish.getComments().contains(comment)) {
            return notFound("There is no comment with the id: " + commentId + " in the fish with the id: " + fishId);
        }

        comment.delete();
        return ok();
    }

    @BodyParser.Of(Json.class)
    public static Result newUser() {
        try {
            JsonNode json = request().body().asJson();
            User userObject = fromJson(json, User.class);
            userObject.save();
            return created();
        } catch (Exception e) {
            if (e instanceof UnrecognizedPropertyException) {
                return badRequest("this Json is not valid");
            } else {
                return internalServerError("Something happened and was not possible persist this fish");
            }
        }
    }

    @BodyParser.Of(Json.class)
    public static Result login() {
        try {
            JsonNode json = request().body().asJson();
            User userRequest = fromJson(json, User.class);
            User realUser = User.FINDER.byId(userRequest.getEmail());
            if (realUser.getPassword().equals(userRequest.getEmail())) {
                return ok();
            } else {
                return badRequest("Invalid Password");
            }

        } catch (Exception e) {
            if (e instanceof UnrecognizedPropertyException) {
                return badRequest("this Json is not valid");
            } else {
                return internalServerError("Something happened and was not possible persist this fish");
            }
        }
    }
}