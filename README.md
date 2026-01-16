## Overview
This project implements a basic HTTP/1.1 server from scratch using raw TCP sockets in Java. 
The goal is to understand networking, HTTP semantics, and server architecture without using frameworks.

## Project Progress Log

### Day 1
- Implemented a minimal TCP server in Java using `ServerSocket`.
- The server listens on port `8080`, accepts a single client, and reads raw bytes from the input stream.
- Verified behavior using `curl` to inspect the raw HTTP request sent by the client.
- No response is returned to the client yet (connection remains open until client closes).
- Objective: observe HTTP over raw TCP without frameworks.

### Day 2
- Refactored the socket reading logic into a separate `ClientHandler` class to improve structure.
- `Main` now only starts the server, accepts the client, and delegates the work.
- No change in functionality: server still prints raw HTTP request data and closes resources afterwards.
- Refactor prepares the codebase for future steps including concurrency and basic HTTP parsing.

### Day 3 — HTTP Request Parsing

Implemented the first stage of HTTP protocol handling:

- Parsed request line (method, path, version) from raw TCP input.
- Parsed headers into a `Map<String, String>`.
- Added `HttpRequest` class for structured representation.

Tested using `curl` and browser; server correctly prints parsed request fields.

Next: routing + HTTP responses.