package ru.job4j.io.json;

import org.json.JSONObject;

public class JsonObjNotebook {
    public static void main(String[] args) {
        Notebook notebook = new Notebook(true, 2000,
                "ASUS", new Suppliers("Restore", "Moscow street1"),
                "Black", "Diagonal 24");

        // в JSONObject
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("lastModel", notebook.isLastModel());
        jsonObject.put("price", notebook.getPrice());
        jsonObject.put("model", notebook.getModel());
        jsonObject.put("suppliers", notebook.getSuppliers());
        jsonObject.put("characteristics", notebook.getCharacteristics());
        System.out.println(jsonObject);

        // в json-строку
        String rslJson = jsonObject.toString();
        System.out.println(rslJson);
    }
}
