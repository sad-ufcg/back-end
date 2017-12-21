# coding: utf-8

import requests
def criaProfessor():
  r = requests.post('http://localhost:8080/professores', json = {
    'siape': '2291146',
    'nome': 'matheusgr'  # TODO: professor nao precisa ter questionarioAplicado
  })
  assert r.status_code == 201
  print 'Professor criado:', r.json()
  return r.json()

def criaQuestionario(professor):
  r = requests.post('http://localhost:8080/questionarios', json = {
    'nome': 'Questionario Estrutural',
    'descricao': 'Um questionário para avaliar a estrutura da sala.',
    'questoes': [
      {
        'enunciado': 'A estrutura da sala demonstra seguir os padrões de segurança?',
        'tipoQuestao':'TEXTO'
      },
      {
        'enunciado': 'De 1-5 qual nota você daria a estrutura da sala?',
        'tipoQuestao':'ESCOLHA_SIMPLES'
      },
      {
        'enunciado': 'Se você pudesse, o que mudaria na sala?',
        'tipoQuestao':'TEXTO'
      }
    ],
    'autor': professor
  })
  assert r.status_code == 201
  print 'Questionário criado:', r.json()
  return r.json()

def criaDisciplina(professor):
    r = requests.post('http://localhost:8080/disciplinas', json = {
        'nome': 'Visão Computacional',
        'turma': '1',
        'semestre': '8',
        'codigo': 'KJSJbkjbdadsbLJaba',
        'professorId': professor['id']
        })
    assert r.status_code == 201
    print 'Disciplina criada:', r.json()
    return r.json()

def criaQuestionarioAplicado(disciplina, questionario):
  r = requests.post('http://localhost:8080/questionariosAplicados', json = {
         'idQuestionario': questionario['id'],
         'idProfessor': questionario['autor']['id'],
         'idDisciplina': disciplina['id'],
         'respostas': []
  })
  assert r.status_code == 201
  print 'Questionário Aplicado criado:', r.json()
  return r.json()

def criaToken(questionarioAplicado):
    r = requests.post('http://localhost:8080/token', json = {
        'idQuestionarioAplicado': questionarioAplicado['id']
    })
    assert r.status_code == 201
    print 'Token criado:', r.json()
    return r.json()


professor = criaProfessor()
disciplina = criaDisciplina(professor)
questionario = criaQuestionario(professor)
questionarioAplicado = criaQuestionarioAplicado(disciplina, questionario)
token = criaToken(questionarioAplicado)

print 'Execute o front-end (angular-ui) e acesse:'
print 'http://localhost:8000/app/#/form/%s' % token['id']
