# SessionHighlighter

This project is used to color requests in burp proxy based on the Chrome profile making the request. It is useful to easily track multiple sessions.

This repository has two components:

- `SessionHighlighterChrome`: a Chrome extension which adds the header `X-Burp-Color` to all requests
- `SessionHighlighterBurp`: a Burp extension which removes the header from incoming requests and colors it accordigly
