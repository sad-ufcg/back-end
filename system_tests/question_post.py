# coding: utf-8 

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
        r = requests.post('http://localhost:8080/disciplinas/csv', files={'file': f})
        assert r.status_code == 401


def upload():
    with open('example.csv', 'rb') as f:
        r = requests.post('http://localhost:8080/disciplinas/csv', files={'file': f}, auth=HTTPBasicAuth('user', 'pass'))
        assert r.status_code == 200


def questionario():
    assert send('questionnaire', {}, False).status_code == 401
    send('questionnaire', {})


def questoes():
    questoes_enun = [
        'Os pré-requisitos assumidos pela disciplina foram adequados.',
        'O programa da disciplina está de acordo com a ementa da mesma.',
        "A metodologia usada pelo professor (recursos didáticos, atividades dentro e fora de sala de aula) teve impacto no aprendizado.",
        "A bibliografia apresentada teve impacto no seu aprendizado.",
        "O professor tem domínio no assunto dado.",
        "O método de avaliação foi apropriado para o conteúdo da disciplina.",
        "Você aprendeu bem o material ministrado na disciplina.",
        "O professor foi pontual.",
        "O professor foi assíduo.",
        "Para as aulas que o professor faltou (no caso, faltas não previstas no cronograma da disciplina), houve reposição.",
        "O professor equilibrou teoria/prática na disciplina (em disciplinas de laboratório, considere como 'teoria' a orientação do professor para os exercícios).",
        "A comunicação da turma com o professor foi bem efetuada.",
        "O professor demonstra preocupação com o aprendizado dos matriculas.",
        "A climatização da sala de aula (ou laboratório) é adequada.",
        "Os recursos didáticos (software, computador, quadro, datashow, etc.) da sala de aula (ou laboratório) são adequados.",
        "O mobiliário (cadeiras, mesas, etc.) da sala de aula (ou laboratório) é adequado.",
        "A limpeza da sala de aula (ou laboratório) é adequada.",
        ]
    
    #TEXTO, MULTIPLA_ESCOLHA, SELECAO
    assert send('question', {'enunciado': "1", 'tipoResposta': 'TEXTO', 'comentario': "", "questionnaire": 1}, False).status_code == 401
    
    for q in questoes_enun:
        send('question', {'enunciado': q, 'tipoResposta': 'MULTIPLA_ESCOLHA', 'comentario': "", "questionnaire": 1})
        send('question', {'enunciado': "Comentário...", 'tipoResposta': 'TEXTO', 'comentario': "", "questionnaire": 1})

    #send('question', {'enunciado': "Banana frita é bom?", 'tipoResposta': 'TEXTO', 'comentario': "", "questionnaire": 1})
    #send('question', {'enunciado': "Rabanada é bom?", 'tipoResposta': 'MULTIPLA_ESCOLHA', 'comentario': "-", "questionnaire": 1})
    #send('question', {'enunciado': "Rabanada é doce?", 'tipoResposta': 'SELECAO', 'comentario': ":)", "questionnaire": 1})


def mailman():
    #TEXTO, MULTIPLA_ESCOLHA, SELECAO
    assert send('mailman', {"questionnaire": 1}, False).status_code == 401
    send('mailman', {"questionnaire": 1})


def tokens(t):
    send('questionnaireanswers', {'tokens': {'id': t},
                                  'answers':
                                  [{'question': {'id': 1}, "answerText": "banana2", "choiceNumber": 1},
                                   {'question': {'id': 2}, "answerText": "banana3", "choiceNumber": 1},
                                   {'question': {'id': 4}, "answerText": "banana2", "choiceNumber": 1}]})




insecure_upload()
upload()
questionario()
questoes()
mailman()

#tokens("xpto")
