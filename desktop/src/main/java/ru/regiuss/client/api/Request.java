package ru.regiuss.client.api;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public interface Request<R, T> {
    HttpRequest.Builder request();
    HttpResponse.BodyHandler<R> type();
    T response(HttpResponse<R> response);
}
