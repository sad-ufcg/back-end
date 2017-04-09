import requests
from requests.auth import HTTPBasicAuth

def _debug(r):
        print("====")
        print(r)
        print(r.text)
        print(r.reason)
        print(r.raw)

def send(resource, json, secure=True):
    if secure:
        r = requests.post('http://localhost:8080/' + resource, json=json, auth=HTTPBasicAuth('user', 'pass'))
    else:
        r = requests.post('http://localhost:8080/' + resource, json=json)
    _debug(r)
    return r

def insecure_upload():
    with open('example.csv', 'rb') as f:
        r = requests.post('http://localhost:8080/upload', files={'file': f})
        assert r.status_code == 401


def upload():
    with open('example.csv', 'rb') as f:
        r = requests.post('http://localhost:8080/upload', files={'file': f}, auth=HTTPBasicAuth('user', 'pass'))
        assert r.status_code == 200


def questionario():
    assert send('questionnaire', {}, False).status_code == 401
    send('questionnaire', {})


def questoes():
    #TEXTO, MULTIPLA_ESCOLHA, SELECAO
    assert send('question', {'enunciado': "1", 'tipoResposta': 'TEXTO', 'comentario': "", "questionnaire": 1}, False).status_code == 401
    send('question', {'enunciado': "Banana frita é bom?", 'tipoResposta': 'TEXTO', 'comentario': "", "questionnaire": 1})
    send('question', {'enunciado': "Rabanada é bom?", 'tipoResposta': 'MULTIPLA_ESCOLHA', 'comentario': "-", "questionnaire": 1})
    send('question', {'enunciado': "Rabanada é doce?", 'tipoResposta': 'SELECAO', 'comentario': ":)", "questionnaire": 1})


def mailman():
    #TEXTO, MULTIPLA_ESCOLHA, SELECAO
    assert send('mailman', {"questionnaire": 1}, False).status_code == 401
    send('mailman', {"questionnaire": 1})


insecure_upload()
upload()
questionario()
questoes()
mailman()