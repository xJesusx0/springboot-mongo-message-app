import requests
from print_messages import print_messages

url = 'http://localhost:8080'

print(f'Intentando conectarse a {url}...')

try:
    response = requests.get(url)
    response_json = response.json()
    if response_json.get('response') == 'ok' and response.status_code == 200:
        print('Conexion con el servidor establecida correctamente')
    else:
        print(f'Respuesta inesperada del servidor: {response.text}')

except requests.RequestException as e:
    print(f'Ha ocurrido un error al intentar conectarse a {url}')
    print(str(e))
    exit()

username = input('Ingrese su nombre: ')

while True:
    message = input('$ ')

    if message.lower() == 'exit':
        break

    data = {
        'username':username,
        'content':message
    }

    response = requests.post(f'{url}/add-message',json = data)
    res = requests.get(f'{url}/get-messages')
    messages = res.json()
    print_messages(messages)
