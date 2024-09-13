package org.mdr.sessionHighlighterBurp;

import burp.api.montoya.core.Annotations;
import burp.api.montoya.core.HighlightColor;
import burp.api.montoya.http.handler.*;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.logging.Logging;

import java.util.Map;

public class BurpColorHandler implements HttpHandler {

	public static String COLOR_HEADER = "X-Burp-Color";
	public static Map<String, HighlightColor> COLORS = Map.of(
			"red", HighlightColor.RED,
			"orange", HighlightColor.ORANGE,
			"yellow", HighlightColor.YELLOW,
			"green", HighlightColor.GREEN,
			"cyan", HighlightColor.CYAN,
			"blue", HighlightColor.BLUE,
			"pink", HighlightColor.PINK,
			"magenta", HighlightColor.MAGENTA,
			"gray", HighlightColor.GRAY
	);

	Logging logger;

	public BurpColorHandler(Logging logger) {
		this.logger = logger;
	}

	@Override
	public RequestToBeSentAction handleHttpRequestToBeSent(HttpRequestToBeSent httpRequestToBeSent) {
		String headerValue = httpRequestToBeSent.headerValue(COLOR_HEADER);

		if (headerValue != null) {
			HighlightColor color = COLORS.getOrDefault(headerValue, null);
			HttpRequest res = httpRequestToBeSent.withRemovedHeader(COLOR_HEADER);

			if (color != null) {
				return RequestToBeSentAction.continueWith(res, Annotations.annotations(color));
			}
		}

		return RequestToBeSentAction.continueWith(httpRequestToBeSent);
	}

	@Override
	public ResponseReceivedAction handleHttpResponseReceived(HttpResponseReceived httpResponseReceived) {
		return ResponseReceivedAction.continueWith(httpResponseReceived);
	}

}
