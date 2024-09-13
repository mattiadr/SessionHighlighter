import { reloadNetRules } from "./common.js"

const colors = {
	"undefined": { foreground: "black", background: "white" },
	"red":       { foreground: "white", background: "#ff6464" },
	"orange":    { foreground: "black", background: "#ffc864" },
	"yellow":    { foreground: "black", background: "#ffff64" },
	"green":     { foreground: "black", background: "#64ff64" },
	"cyan":      { foreground: "black", background: "#64ffff" },
	"blue":      { foreground: "white", background: "#6464ff" },
	"pink":      { foreground: "black", background: "#ffc8c8" },
	"magenta":   { foreground: "black", background: "#ff64ff" },
	"gray":      { foreground: "black", background: "#b4b4b4" },
}

const selector = document.getElementById("color-selector");

function doColor(element, color) {
	const c = colors[color];
	element.style.color = c.foreground;
	element.style.backgroundColor = c.background;
}

for (let option of selector.children) {
	doColor(option, option.value);
}

selector.addEventListener("change", function onChangeSelector() {
	doColor(selector, this.value);
	chrome.storage.local.set({ color: this.value }, reloadNetRules);
});

chrome.storage.local.get(["color"], (result) => {
	doColor(selector, result.color);
	selector.value = result.color;
});
