import sys

notation = sys.stdin.readline().rstrip()
operator = []
result = []

for x in notation:
    if(x.isalpha()):
        result.append(x)
    else:
        if(x == "("):
            operator.append(x)
        elif(x == ")"): # "(" : 나올 때 까지 출력
            while(operator[-1] != "("):
                result.append(operator.pop())
            operator.pop()
        elif(x == "*" or x == "/"): # 같은 연산자가 나올 때 까지만 출력
            while(operator):
                if(operator[-1] == "*" or operator[-1] == "/"):
                    result.append(operator.pop())
                else:
                    break
            operator.append(x)
        elif(x == "+" or x == "-"): # '+' or '-' : 모든 사칙연산을 먼저 출력
                while(operator and operator[-1] != "(" and operator[-1] != ")"):
                    result.append(operator.pop())
                operator.append(x)

while(operator):
    result.append(operator.pop())

print(''.join(result))