# FindWords

## SDET Homework Problem  

_Erich Tinguely 1/25/21_  

Assumptions
- This program will find ASCII character-based words only
- If no words can be found it should just return an empty array
- Prefix or suffix spaces are ignored
- Any non-alphabet character is ignored (e.g. A-Z and a-z only)
- As implemented, no string length check is performed -- very long strings will take a very long time to process
- Currently exceptions are not caught, such as IOExceptions or MalformedURLExceptions
- Strings are case insensitive, "AB" is the same as "ab"
- the Dictionary.IsEnglishWord() method assumes an English dictionary


Note that portions of this code are authored by robau ( https://codereview.stackexchange.com/users/21279/robau )
From code located at https://codereview.stackexchange.com/questions/166281/find-all-words-in-a-dictionary-that-can-be-made-with-a-string-of-characters-rec

