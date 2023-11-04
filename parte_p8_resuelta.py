#Importo librerías a utilizar:
from queue import LifoQueue as pila, Queue as cola
import random


#Parte 1: Archivos


def contar_lineas(nombre_archivo:str) -> int:
    archivo = open(nombre_archivo, "r")
    return len(archivo.readlines())

def existe_palabra(nombre_archivo:str, palabra:str) -> bool:
    archivo = open(nombre_archivo, "r") 
    contenido = archivo.read()
    return palabra in contenido.split()


def cantidad_aparicones(nombre_archivo:str, palabra:str) -> int:
    archivo = open(nombre_archivo, 'r')
    palabras_archivo = archivo.read()
    return palabras_archivo.split().count(palabra)


def clonar_sin_comentarios(nombre_archivo:str) -> str:
    archivo = open(nombre_archivo, 'r')
    archivo_descomentado = open('/home/clinux01/Descargas/archivo_descomentado.txt', 'w')
    palabras_por_linea = archivo.readlines()
    for linea in palabras_por_linea:
        if linea.strip()[0] != '#':
           archivo_descomentado.write(linea)


def reverso(nombre_archivo:str, nombre_nuevo_archivo:str) -> str:
    archivo = open(nombre_archivo, 'r')
    archivo_nuevo:str = open(nombre_nuevo_archivo, 'w')
    lineas_orden_inverso:list[str] = archivo.readlines()[-1::-1]
    for linea in lineas_orden_inverso:
        if '\n' not in linea:
            archivo_nuevo.write(linea+'\n')

        else : archivo_nuevo.write(linea)
    archivo.close()
    archivo_nuevo.close()

archivo = '/home/carbp/Downloads/pruebita.txt'
archivo_nuevo = '/home/carbp/Downloads/pruebita_output.txt'

def frase_al_final(nombre_archivo:str,frase:str):
    archivo = open(nombre_archivo, 'r')
    lineas:list[str] = archivo.readlines()
    lineas.append(frase)
    archivo_para_escribir = open(nombre_archivo, 'w')
    for linea in lineas:
        archivo_para_escribir.write(linea)

frase = 'keloke bichoooo'

#frase_al_final(archivo,'epa broooo dame caramelo')

def frase_al_principio(nombre_archivo:str,frase:str):
    archivo = open(nombre_archivo, 'r')
    lineas:list[str] = archivo.readlines()
    lineas.insert(0,frase+'\n')
    archivo = open(nombre_archivo, 'w')
    for linea in lineas: 
        archivo.write(linea)


notas = '/home/carbp/Downloads/doc_notas.txt'

def promedioEstudiantes(nombre_archivo:str, lu:str) -> int:
    archivo_csv = open(nombre_archivo, 'r')
    contenido_por_lineas:[str] = archivo_csv.readlines()
    estudiante_nota:[float] = []
    #ejemplo de linea: nro de LU ( str ) , materia ( str ) , fecha ( str ) , nota ( float )
    for linea in contenido_por_lineas:
        linea_seperada_por_comas:[str] = linea.split(',')
        lu_en_linea:str = linea_seperada_por_comas[0].split()[-1]
        nota:float= float(linea_seperada_por_comas[-1].split()[-1])
        if lu_en_linea == lu:
            estudiante_nota.append(nota)
    return sum(estudiante_nota)/len(estudiante_nota)

            
#print(clonar_sin_comentarios('/home/clinux01/Descargas/ejemplo.txt'))


# El resto de ejercicios...

# Parte 2:Pilas

from queue import LifoQueue as pila

def generar_nros_al_azar(n:int,desde:int,hasta:int) -> pila:
    nros_azarosos:pila = pila()
    for i in range(n):
        nros_azarosos.put(random.randint(desde,hasta))
    return nros_azarosos

def cantidad_elementos(p:pila) -> int:
    contador:int = 0
    pila_auxiliar:pila = pila()
    if p.empty():
        return 0
    else:
        while not p.empty():
            valor_desapilado = p.get()
            pila_auxiliar.put(valor_desapilado)
            contador += 1
        while not pila_auxiliar.empty():
            valor_desapilado = pila_auxiliar.get()
            p.put(valor_desapilado)
        return contador

ejemplo = generar_nros_al_azar(17, 1, 9)


            
            

def buscar_el_maximo(p:pila) -> int:
    elementos:list[int] = []
    pila_auxiliar:pila = pila()
    while not p.empty():
        elemento = p.get
        elementos.append(elemento)
        pila_auxiliar.put(elemento)
    while not pila_auxiliar.empty():
        elemento = pila_auxiliar.get()
        pila.put()
    return max(elementos)


# Parte 3: Colas ...
#Colas
def generar_nros_al_azar(n:int, desde:int, hasta:int) -> cola:
    cola_resultado:cola = cola()
    for i in range(n):
        entero_azaroso:int = random.randint(desde,hasta)
        cola_resultado.put(entero_azaroso)
    return cola_resultado


def cantidad_elementos(c:cola) -> int:
    contador:int = 0
    c_auxiliar:cola = cola()
    while not c.empty():
        c_auxiliar.put(c.get())
        contador += 1
    while not c_auxiliar.empty():
        c.put(c_auxiliar.get())
    return contador

cola_ejemplo = generar_nros_al_azar(10,1,10)
#print(cantidad_elementos(cola_ejemplo))

def buscar_el_maximo(c:cola) -> int:
    elementos:[int] = []
    c_auxiliar:cola = cola()

    while not c.empty():
        elemento:int = c.get()
        elementos.append(elemento)
        c_auxiliar.put(elemento)
    while not c_auxiliar.empty():
        elemento:int = c_auxiliar.get()
        c.put(elemento)
    return max(elementos)

def armar_secuencia_de_bingo() -> cola:
    valores:list[int] = []
    res:cola = cola()
    for i in range(100):
        valores.append(i)
    while len(valores) > 0:
        valor_aleatorio:int = random.choice(valores)
        valores.remove(valor_aleatorio)
        res.put(valor_aleatorio)
    return res

def elementos_de_cola(c:cola) -> list:
    elementos:list = []
    cola_auxiliar:cola = cola()
    while not c.empty():
        elemento = c.get()
        elementos.append(elemento)
        cola_auxiliar.put(elemento)
    while not cola_auxiliar.empty():
        elemento = cola_auxiliar.get()
        c.put(elemento)
    return elementos

def jugar_carton_bingo(carton:list[int], bolillas:cola) -> int:
    carton_copia:list[int] = carton.copy()
    cola_auxiliar:cola = cola()
    contador_jugadas:int = 0

    while len(carton_copia) > 0:
        bolilla_sacada:int = bolillas.get()
        if bolilla_sacada in carton_copia:
            carton_copia.remove(bolilla_sacada)
        contador_jugadas += 1
    
    while not bolillas.empty():
        elemento = bolillas.get()
        cola_auxiliar.put(elemento)
    while not cola_auxiliar.empty():
        elemento = cola_auxiliar.get()
        bolillas.put(elemento)

    return contador_jugadas



bolillas = armar_secuencia_de_bingo()
carton = [1,2,3,4,5,6,7,8,9,10]

#print(jugar_carton_bingo(carton,bolillas))

def n_pacientes_urgentes(pacientes:cola[(int,str,str)]) -> int:
    pacientes_urgentes:int = 0
    cola_auxiliar:cola = cola()

    while not pacientes.empty():
        paciente = pacientes.get()
        cola_auxiliar.put(paciente)
        if paciente[0] in (1,2,3):
            pacientes_urgentes += 1
    while not cola_auxiliar.empty():
        elemento = cola_auxiliar.get()
        pacientes.put(elemento)
    return pacientes_urgentes

#a clientes(in c : Cola[(str, int, bool, bool)]) → Cola[(str, int, bool, bool) Uno preferencial seria: ('ROberto Salazar',445896,Preferencial=True,Prioritario=True)

def atencion_clientes(c_entrada:cola) -> cola:
    cola_prioritarios:cola = cola()
    cola_cuentistas_preferenciales:cola = cola()
    cola_comunes:cola = cola()
    lista_de_colas:list = [cola_prioritarios,cola_cuentistas_preferenciales,cola_comunes]
    cola_final:cola = cola()

    cola_auxiliar:cola = cola()
    while not c_entrada.empty():
        persona_en_cola:tuple = c_entrada.get()
        if persona_en_cola[3] == True: #su ultimo atributo es True, es decir es mayor, embarazada o movilidad reducida
            cola_prioritarios.put(persona_en_cola)
        elif persona_en_cola[2] == True: #tiene cuenta preferencial.
            cola_cuentistas_preferenciales.put(persona_en_cola)
        else: 
            cola_comunes.put(persona_en_cola)
        cola_auxiliar.put(persona_en_cola)  #En cualquier caso, se anexa la tupla a la auxiliar

    #se vacian las colas en una sola:
    for cola_actual in lista_de_colas:
        while not cola_actual.empty():
            persona_en_cola = cola_actual.get()
            cola_final.put(persona_en_cola)
    
    #se restaura la cola original con la cola_auxiliar
    while not cola_auxiliar.empty():
        persona_en_cola = cola_auxiliar.get()
        c_entrada.put(persona_en_cola)
    
    return cola_final
'''
c = cola()
lista_pacientes = [('Roberto',9586,False,True),('Anfel',95874,True,False),('Lucio',87591,True,False),('Argio',84114,False,True),('Santi',98741,True,True),('Sebas',9874,False,False)]
#output esperado: Roberto, Argio, Santi, Anfel, Lucio,Sebas
for paciente in lista_pacientes:
    c.put(paciente)

cola_ejemplo = atencion_clientes(c)
print(elementos_de_cola(cola_ejemplo))
'''

#Parte 4: Diccionarios

def agrupar_por_longitud(nombre_archivo:str) -> dict:
    archivo = open(nombre_archivo, 'r')
    lista_palabras = archivo.read().split()
    longitudes:dict = {}
    
    for palabra in lista_palabras:
        longitud = len(palabra)
        if longitud not in longitudes:
            longitudes[longitud] = 1
        else:
            longitudes[longitud] += 1
    return longitudes


def suma(l:list) -> float:
    suma = 0
    for elem in l:
        suma += elem
    return suma


def promedioEstudiantesDiccionario(nombre_archivo:str) -> dict:
    archivo = open(nombre_archivo,'r')
    lineas_en_archivo:list = archivo.readlines()
    estudiantes_y_notas:dict = {}
                                            #nro de LU ( str ) , materia ( str ) , fecha ( str ) , nota ( float )
    for linea_actual in lineas_en_archivo:
        linea_actual = linea_actual.split(',')
        alumno_nro:str = linea_actual[0].split()[-1]
        nota:float = float(linea_actual[-1].split()[-1])
        if alumno_nro not in estudiantes_y_notas:
            estudiantes_y_notas[alumno_nro] = []
            estudiantes_y_notas[alumno_nro].append(nota)
        else:
            estudiantes_y_notas[alumno_nro].append(nota)
    for estudiante in estudiantes_y_notas:
        suma_notas:float = sum(estudiantes_y_notas[estudiante])
        cantidad_notas:int = len(estudiantes_y_notas[estudiante])
        estudiantes_y_notas[estudiante] = suma_notas/cantidad_notas
    return estudiantes_y_notas

archivo_prueba = '/home/carbp/Downloads/notas_alumnos.txt'
#print(promedioEstudiantesDiccionario(archivo_prueba))

def agrupar_por_longitud(nombre_archivo:str) -> dict:
    archivo = open(nombre_archivo, 'r')
    lista_palabras = archivo.read().split()
    longitudes:dict = {}
    
    for palabra in lista_palabras:
        longitud = len(palabra)
        if longitud not in longitudes:
            longitudes[longitud] = 1
        else:
            longitudes[longitud] += 1
    return longitudes

def palabra_mas_frecuente(nombre_archivo:str) -> str:
    archivo = open(nombre_archivo,'r')
    contenido:list = archivo.read().split()
    apariciones_palabras:dict = {}
    palabra_mayor_aparicion:str = ''

    for palabra in contenido:
        if palabra not in apariciones_palabras:
            apariciones_palabras[palabra] = 1
        else: 
            apariciones_palabras[palabra] += 1
    numero_maximo_apariciones:int = 0
    for palabra in apariciones_palabras:
        numero_apariciones:int = apariciones_palabras[palabra]
        if numero_apariciones >= numero_maximo_apariciones:
            numero_maximo_apariciones,palabra_mayor_aparicion = numero_apariciones,palabra
    return palabra_mayor_aparicion

historiales:dict = {}
ultima_navegacion_hacia_atras:pila = pila()
def visitar_sitio(historial:dict,usuario:str,sitio:str):
    if usuario not in historial:
        historial[usuario] = pila()
        historial[usuario].put(sitio)
    else:
        historial[usuario].put(sitio)
    
def navegar_atras(historial:dict,usuario:str):
    busqueda = historial[usuario].get()
    ultima_navegacion_hacia_atras.put(busqueda)

def navegar_adelante(historial:dict,usuario:str):
    busqueda = ultima_navegacion_hacia_atras.get()
    historial.put(busqueda)




'''Implementa una función llamada agregar producto(inventario, nombre, precio, cantidad) que permita agregar un
nuevo producto al inventario. El nombre del producto debe ser la clave del diccionario, y el valor debe ser otro diccionario
con las claves “precio” y “cantidad”. Como precondición de esta función se requiere que el producto a agregar no esté en
el inventario.
2. Implementa una función llamada actualizar stock(inventario, nombre, cantidad) que permita actualizar la cantidad
de un producto existente en el inventario.
3. Implementa una función llamada actualizar precios(inventario, nombre, precio) que permita actualizar el precio
de un producto existente en el inventario.
4. Implementa una función llamada calcular valor inventario(inventario) que calcule el valor total del inventario mul-
tiplicando el precio por la cantidad de cada producto y sumando los valores de todos los productos.
Ejemplo de uso:
inventario = {}
agregar_producto(inventario, "Camisa", 20.0, 50)
agregar_producto(inventario, "Pantalón", 30.0, 30)
actualizar_existencias(inventario, "Camisa", 10)
valor_total = calcular_valor_inventario(inventario)
print("Valor total del inventario:", valor_total) # Deberı́a imprimir 1300.00'''
inventario:dict = {}

def agregar_producto(inventario:dict,nombre:str,precio:float,cantidad:int):
    if nombre not in inventario:
        inventario[nombre] = {'precio':precio,'cantidad':cantidad}

def actualizar_stock(inventario:dict,nombre:str,cantidad:int):
    inventario[nombre]['cantidad'] = cantidad

def actualizar_precio(inventario:dict,nombre:str,precio:int):
    inventario[nombre]['precio'] = precio

agregar_producto(inventario,'procesador_1',4500,1000)
print(inventario)

actualizar_precio(inventario,'procesador_1',4800)
print(inventario)

actualizar_stock(inventario,'procesador_1',2900)
print(inventario)


    


'''



#Hacer ejercicios de lectura de bytes, parentesis balanceados y polacos.   
        
'''
            
        