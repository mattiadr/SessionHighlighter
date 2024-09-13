package org.mdr.sessionHighlighterBurp;

import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import burp.api.montoya.logging.Logging;

public class SessionHighlighter implements BurpExtension {

	MontoyaApi api;
	Logging logger;

	@Override
	public void initialize(MontoyaApi montoyaApi) {
		api = montoyaApi;
		api.extension().setName("SessionHighlighter");
		logger = api.logging();
		api.http().registerHttpHandler(new BurpColorHandler(logger));
	}

}
