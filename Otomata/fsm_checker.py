def cek_string(input_string):
    state = "S"

    for char in input_string:
        if state == "S":
            if char == '0':
                state = "A"
            elif char == '1':
                state = "B"
            else:
                return False

        elif state == "A":
            if char == '0':
                state = "C"
            elif char == '1':
                state = "B"

        elif state == "B":
            if char == '0':
                state = "A"
            elif char == '1':
                state = "B"

        elif state == "C":
            state = "C"

    return state == "B"


# ===== MAIN PROGRAM =====
print("=== FSM CHECKER ===")
print("Aturan:")
print("- Hanya boleh 0 dan 1")
print("- Tidak boleh ada '00'")
print("- Harus berakhir dengan 1\n")

string = input("Masukkan string: ")

if cek_string(string):
    print("✅ DITERIMA (Accepted)")
else:
    print("❌ DITOLAK (Rejected)")