const allResourceTypes = Object.values(chrome.declarativeNetRequest.ResourceType);

export function reloadNetRules() {
	chrome.storage.local.get(['color'], (result) => updateNetRules(result.color));
}

function updateNetRules(color=undefined) {
	chrome.action.setIcon({ path: `icons/${color}_128x128.png` });

	if (color && color != "undefined") {
		const rules = [
			{
				id: 1,
				priority: 1,
				action: {
					type: chrome.declarativeNetRequest.RuleActionType.MODIFY_HEADERS,
					requestHeaders: [
						{
							operation: chrome.declarativeNetRequest.HeaderOperation.SET,
							header: "X-Burp-Color",
							value: color,
						},
					]
				},
				condition: {
					urlFilter: "*",
					resourceTypes: allResourceTypes,
				}
			}
		];

		chrome.declarativeNetRequest.updateDynamicRules({
			removeRuleIds: [1],
			addRules: rules
		});
	} else {
		chrome.declarativeNetRequest.updateDynamicRules({
			removeRuleIds: [1],
		});
	}
}
