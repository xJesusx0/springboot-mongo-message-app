#{'_id': {'timestamp': 1723683546, 'date': '2024-08-15T00:59:06.000+00:00'}, 'nombre': 'Jesus', 'mensaje': 'hola'}

def print_messages(messages:list):
    for message in messages:
        print(f'{message["nombre"]}: {message["mensaje"]}')
