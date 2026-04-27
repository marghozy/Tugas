import re

reserved_words = {"int", "float", "if", "else", "while", "return"}
symbols = {"=", "+", "-", "*", "/", "(", ")", "{", "}", ";", ","}

def tokenize(code):
    tokens = re.findall(r'\w+|[^\s\w]', code)
    
    hasil = {
        "reserved": [],
        "simbol": [],
        "variabel": [],
        "matematika": []
    }

    for t in tokens:
        if t in reserved_words:
            hasil["reserved"].append(t)
        elif t in symbols:
            hasil["simbol"].append(t)
        elif re.match(r'^\d+$', t):
            hasil["matematika"].append(t)
        elif re.match(r'^[a-zA-Z_]\w*$', t):
            hasil["variabel"].append(t)

    return hasil


code = input("Masukkan kode program:\n")

hasil = tokenize(code)

print("\n=== HASIL TOKEN ===")
for k, v in hasil.items():
    print(f"{k.upper()} : {', '.join(v)}")
