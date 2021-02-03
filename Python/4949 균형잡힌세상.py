# 백준 4949
result = []
while True:
    brackets = [-1]
    sentence = input()
    if sentence == ".": 
        break
    else:
        for i in sentence:
            if i == '(' or i == '[':
                brackets.append(i)
            elif i == ')' :
                if brackets[-1] == '(' :
                    brackets.pop()
                else:
                    brackets.append(i)
                    break
            elif i == ']':
                if brackets[-1] == '[':
                    brackets.pop()
                else:
                    brackets.append(i)
                    break
        if len(brackets) == 1:
            result.append('yes')
        else:
            result.append('no')            
for i in result:
    print(i)