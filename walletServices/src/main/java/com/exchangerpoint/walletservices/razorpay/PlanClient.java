package com.exchangerpoint.walletservices.razorpay;

import java.util.List;

import org.json.JSONObject;

public class PlanClient extends ApiClient {

    PlanClient(String auth) {
        super(auth);
    }

    public Plan create(JSONObject request) throws RazorpayException {
        return post(Constants.PLAN_CREATE, request);
    }

    public Plan fetch(String id) throws RazorpayException {
        return get(String.format(Constants.PLAN_GET, id), null);
    }

    public List<Plan> fetchAll() throws RazorpayException {
        return fetchAll(null);
    }

    public List<Plan> fetchAll(JSONObject request) throws RazorpayException {
        return getCollection(Constants.PLAN_LIST, request);
    }
}
