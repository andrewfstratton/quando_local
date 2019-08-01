from http.server import HTTPServer, BaseHTTPRequestHandler

class SimpleHTTPRequestHandler(BaseHTTPRequestHandler):

    def do_GET(self):
        self.send_response(200)
        self.end_headers()
        self.wfile.write(b'Quando <//> Local')


httpd = HTTPServer(('127.0.0.1', 8080), SimpleHTTPRequestHandler)
httpd.serve_forever()
