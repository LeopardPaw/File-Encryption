File-Encryption
===============
1. This program encrypts a file using three user defined keys
   of type long(64 bit)
2. The keys themselves are encrypted and written to the file
3. The decryption algorithm reads in the first three longs 
   and converts them into the three original keys
4. The decryption algorithm attempts to write a new file with
   the title <input-file>Decrypted
5. The decryption algorithm reads in each character(16 bit)
   and then runs the reverse of the encryption algorrithm on 
   it before writing it to the output file
