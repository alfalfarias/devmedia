/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

/**
 *
 * @author simon
 */

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Supply;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import static play.mvc.Results.ok;

public class Supplies extends Controller {
    
    public static Result list() {
        ObjectNode result = Json.newObject();
        result.put("supplies", Json.toJson(Supply.all()));
        return ok(result);
    }
    
    /**
     * Handle the 'new supply form' submission 
     * @return 
     */
    public static Result save() {
        JsonNode json = request().body().asJson();
        ObjectNode result = Json.newObject();
        if (json == null){
            result.put("message", "Expecting Json data");
            return badRequest(result);
        }
        Supply supply = (Supply) Json.fromJson(json, Supply.class);
        supply.save();
        result.put("message", "OK");
        result.put("supply", Json.toJson(supply));
        return ok(result);
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the supply to edit
     * @return 
     */
    public static Result retrieve(Long id) {
        ObjectNode result = Json.newObject();
        Supply supply = Supply.find.byId(id);
        if (supply == null){
            result.put("message", "Supply not found");
            return badRequest(result);
        }
        result.put("supply", Json.toJson(supply));
        result.put("message", "OK");
        return ok(result);
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the supply to edit
     * @return 
     */
    public static Result update(Long id) {
        JsonNode json = request().body().asJson();
        ObjectNode result = Json.newObject();
        Supply supply = Supply.find.byId(id);
        if (json == null){
            result.put("message", "Expecting Json data");
            return badRequest(result);
        }
        if (supply == null){
            result.put("message", "Supply not found");
            return badRequest(result);
        }
        supply.name = json.findPath("name").textValue();
        supply.quantity = json.findPath("quantity").intValue();
        result.put("message", "OK");
        result.put("supply", Json.toJson(supply));
        return ok(result);
    }
    
    /**
     * Handle supply deletion
     * @param id
     * @return 
     */
    public static Result delete(Long id) {
        ObjectNode result = Json.newObject();
        Supply supply = Supply.find.byId(id);
        if (supply == null){
            result.put("message", "Supply not found");
            return badRequest(result);
        }
        supply.delete();
        result.put("message", "OK");
        result.put("supply", Json.toJson(supply));
        return ok(result);
    }
}