# coding: utf-8

import json
import requests

def print_criacao(entidade, resposta):
  print '%s criado(a):' % entidade
  print json.dumps(resposta, sort_keys=True, indent=4, separators=(',', ': '))
  print

def criaProfessor():
  r = requests.post('http://localhost:8080/professores', json = {
    'siape': '2291146',
    'nome': 'matheusgr'
  })
  assert r.status_code == 201
  print_criacao('Professor', r.json())
  return r.json()

def criaQuestionario(professor):
  r = requests.post('http://localhost:8080/questionarios', json = {
    'nome': 'Questionario Estrutural',
    'descricao': 'Um questionário para avaliar a estrutura da sala.',
    'questoes': [
      {
        'enunciado': 'A estrutura da sala demonstra seguir os padrões de segurança?',
        'tipoQuestao': 'ABERTA',
        'autor': professor
      },
      {
        'enunciado': 'De 1-5 qual nota você daria a estrutura da sala?',
        'tipoQuestao': 'ESCOLHA_SIMPLES',
        'autor': professor
      },
      {
        'enunciado': 'Se você pudesse, o que mudaria na sala?',
        'tipoQuestao': 'ABERTA',
        'autor': professor
      }
    ],
    'autor': professor
  })
  assert r.status_code == 201
  
  print_criacao('Questionário', r.json())
  return r.json()

def criaDisciplina(professor):
    r = requests.post('http://localhost:8080/disciplinas', json = {
        'nome': 'Visão Computacional',
        'turma': '1',
        'semestre': '2017.2',
        'codigo': '1234567',
        'professorId': professor['id']
    })
    assert r.status_code == 201
    print_criacao('Disciplina', r.json())
    return r.json()

def criaQuestionarioAplicado(disciplina, questionario):
  r = requests.post('http://localhost:8080/questionariosAplicados', json = {
         'idQuestionario': questionario['id'],
         'idProfessor': questionario['autor']['id'],
         'idDisciplina': disciplina['id'],
         'respostas': []
  })
  assert r.status_code == 201
  print_criacao('Questionário Aplicado', r.json())
  return r.json()

def criaToken(questionarioAplicado):
    r = requests.post('http://localhost:8080/token', json = {
        'idQuestionarioAplicado': questionarioAplicado['id']
    })
    assert r.status_code == 201
    print_criacao('Token', r.json())
    return r.json()


professor = criaProfessor()
disciplina = criaDisciplina(professor)
questionario = criaQuestionario(professor)
questionarioAplicado = criaQuestionarioAplicado(disciplina, questionario)
token = criaToken(questionarioAplicado)

print 'Execute o front-end (angular-ui) e acesse:'
print 'http://localhost:8000/app/#/form/%s' % token['id']
