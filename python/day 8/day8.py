def main():
    f = open('input.txt', 'r')
    inputStrings = f.readlines()

    codeLength = 0;
    memoryLength = 0;
    encodedLength = 0;
    for string in inputStrings:
        string = string[:-1]
        decodedString = string[1:-1].decode('string_escape')
        encodedString = '"' + string.encode('string_escape').replace('"', '\\"') + '"'

        codeLength += len(string)
        memoryLength += len(decodedString)
        encodedLength += len(encodedString)

        print string, " -> ", decodedString, " -> ", encodedString

    print 'code length - memory Length', codeLength - memoryLength
    print 'encoded length - code length', encodedLength - codeLength
            
if __name__ == '__main__':
    main()