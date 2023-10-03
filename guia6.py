# Ejercio 1
import math

def imprimir_hola_mundo() -> str:
    print("¡Hola mundo!")


def imprimir_un_verso() -> str:
    print("Hola \nke tal")


def raizDe2() -> float:
    return round(2**(1/2), 4)

def factorial_2() -> int:
    return math.factorial(2)

def perimetro() -> float:
    return 2*math.pi

# Ejercicio 2

def imprimir_saludo(nombre:str) -> str:
    print(f"Hola {nombre}")
    
def raiz_cuadrada_de(x:float) -> float:
    return x**(1/2)

def fahrenheit_a_celsius(t:float) -> float:
    return ((t-32)*5)/9

def imprimir_dos_veces(estribillo:str) -> float:
    print(estribillo*2)
    
def es_multiplo_de(n:int, m:int) -> bool:
    if math.remainder(n,m) == 0:
        return True
    else: return False
    
def es_par(numero:int) -> bool:
    if es_multiplo_de(numero,2) == True:
        return True
    else: return False

def cantidad_de_pizzas(comensales:int, min_cant_de_porciones:int) -> int:
    porciones_totales = comensales*min_cant_de_porciones
    if math.remainder(porciones_totales, 8) != 0:
        return int((porciones_totales/8) + 1)

#Ejercicio 3

def alguno_es_0(numero1:float, numero2:float) -> bool:
    return numero1 == 0 or numero2 == 0

def ambos_son_0(numero1:float, numero2:float) -> bool:
    return numero1 == 0 and numero2 == 0

def es_nombre_largo(nombre:str) -> bool:
    largo = len(nombre)
    return 3 <= largo and largo <= 8

def es_bisiesto(año:int) -> bool:
    return es_multiplo_de(año,400) or (es_multiplo_de(año,4) and (es_multiplo_de(año,100) == False))

#Ejercicio 4

def peso_pino(altura:int) -> int:
    if altura > 300:
        return (altura - 300)*2 + 300*3
    else: 
        return altura * 3
    
def es_peso_util(peso:int) -> bool:
    return min(400,1000,peso) == 400 and max(400,1000,peso) == 1000

def sirve_pino(altura:int) -> bool:
    return min(400,1000,peso_pino(altura)) == 400 and max(400,1000,peso_pino(altura)) == 1000

def sirve_pino_compuesta(altura:int) -> bool:
    return sirve_pino(altura)


#Ejercicio 5

def devolver_el_doble_si_es_par(numero:int) -> int:
    if es_multiplo_de(numero, 2):
        return numero*2
    else: 
        return numero
    
def devolver_valor_si_es_par_sino_el_que_sigue(numero:int) -> int:
    if es_multiplo_de(numero,2):
        return numero
    else:
        return numero + 1
    
def devolver_doble_multiplo3_triple_multiplo9(numero:int) -> int:
    if es_multiplo_de(numero,9):
        return 3*numero
    if es_multiplo_de(numero,3):
        return 2*numero
    else: 
        return numero
    
def lindo_nombre(nombre:str) -> str:
    longitud = len(nombre)
    if longitud >= 5:
        return "Tu nombre tiene muchas letras!"
    else: 
        return "Tu nombre tiene menos de 5 caracteres"
    
def elRango(numero:int) -> int:
    if numero < 5:
        print("Menor a 5")
    if numero >= 10 and numero <= 20:
        print("Entre 10 y 20")
    if numero > 20:
        print("Mayor a 20")
        
def levantar_pala(sexo:str, edad:int) -> str:
    if (sexo=="F" and edad>=60) or (sexo=="M" and edad>=65) or edad < 18:
        print("Andá de vacaciones")
    else:
        print("Te toca trabajar")
        
# Ejercicio 6

def imprime_hasta_10() -> int:
    n = 1
    while n <= 10:
        print(n)
        n += 1
        
def pares_entre_10_40() -> int:
    n = 10
    while n <= 40:
        print(n)
        n += 2
        
def eco() -> str:
    n = 0
    while n <= 9:
        print("eco")
        
def cuenta_regresiva(numero:int) -> str:
    while numero >= 1:
        print(f"{numero}")
        numero -= 1
    print("Despegue")
    

def viaje_temporal(partida:int,llegada:int) -> str:
    while partida > llegada:
        partida -= 1
        print(f"Viajó un año en el pasado. Estamos en el año {partida}")
    
def viaje_temporal_aristotelico(partida:int) -> str:
    while (partida - 20) >= (-384):
        partida -= 20
        print(f"Viajó 20 años en el pasado. Estamos en el año {partida}")
      
# Ejercicio 7

def for_imprime_hasta_10() -> int:
    for i in range(1,11):
        print(i)

def for_pares_entre_10_40() -> int:
    for i in range(10,41):
        if es_multiplo_de(i,2):
            print(i)

for_pares_entre_10_40()

#Ejercicio 8

#Ejercicio 9

def rt(x:int,g:int) -> int:
    g = g + 1
    return x + g

g:int = 0
def ro(x:int) -> int:
    global g
    g = g +1
    return x + g

'''
1. ¿Cuál es el resultado de evaluar tres veces seguidas ro(1)?
-> El resultado es 2,3,4
2. ¿Cuál es el resultado de evaluar tres veces seguidas rt(1, 0)?
-> El resultado es 2, 2, 2
3. En cada función, realizar la ejecución simbólica.
-> En "ro", el valor de g va cambiando.
4. Dar la especificación en lenguaje natural para cada función, rt y ro.
problema rt(in x:Int, inout g:Int) : Int {
    requiere: {True}
    asegura: {g2 = g + 1}
    asegura: {res = x + g2}
}

problema ro(in x:Int) : Int {
    requiere: {True}
    asegura: {En el estado previo a la primera ejecución de ro, g = 0}
    asegura: {En el estado pos-ejecución de esta función, g (variable global) es igual a g@pre + 1}
    asegura: {res = x + g}
}

'''
