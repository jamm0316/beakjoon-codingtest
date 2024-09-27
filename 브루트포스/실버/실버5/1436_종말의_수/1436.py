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


---

#case 2
def find_end_of_world_number(N):
    number, count = 666, 0
    
    while count < N:
        if '666' in str(number):
            count += 1
        number += 1
    
    return number - 1

N = int(input("찾고 싶은 종말의 숫자를 입력하세요: "))
print(find_end_of_world_number(N))
