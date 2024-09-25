def find_end_of_world_number(N):
    count = 0  
    number = 666
    
    while True:
        if '666' in str(number):
            count += 1 
            if count == N:
                return number
        number += 1  

N = int(input())
print(find_end_of_world_number(N))
