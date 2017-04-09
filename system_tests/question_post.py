import requests
from requests.auth import HTTPBasicAuth

def _debug(r):
        print("====")
        print(r)
        print(r.text)
        print(r.reason)
        print(r.raw)

def send(resource, json):
    r = requests.post('http://localhost:8080/' + resource, json=json, auth=HTTPBasicAuth('user', 'pass'))
    _debug(r)

def questionario():
    send('questionnaire', {})

def questoes():
    #TEXTO, MULTIPLA_ESCOLHA, SELECAO
    send('question', {'enunciado': "Banana frita é bom?", 'tipoResposta': 'TEXTO', 'comentario': "", "questionnaire": 1})
    send('question', {'enunciado': "Rabanada é bom?", 'tipoResposta': 'MULTIPLA_ESCOLHA', 'comentario': "-", "questionnaire": 1})
    send('question', {'enunciado': "Rabanada é doce?", 'tipoResposta': 'SELECAO', 'comentario': ":)", "questionnaire": 1})

questionario()
questoes()