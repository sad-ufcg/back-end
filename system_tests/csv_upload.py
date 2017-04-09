import requests
from requests.auth import HTTPBasicAuth

def _debug(r):
        print(r)
        print(r.text)
        print(r.reason)
        print(r.raw)

def test_insecure_upload():
    with open('example.csv', 'rb') as f:
        r = requests.post('http://localhost:8080/upload', files={'file': f})
        assert r.status_code == 401

def test_upload():
    with open('example.csv', 'rb') as f:
        r = requests.post('http://localhost:8080/upload', files={'file': f}, auth=HTTPBasicAuth('user', 'pass'))
        assert r.status_code == 200

test_insecure_upload()
test_upload()
