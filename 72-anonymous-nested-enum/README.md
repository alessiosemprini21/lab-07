# Classi anonime, classi innestate, enumerazioni

**Fare correggere ad ogni parte dell'esercizio, non solo al termine di tutte le parti.**

## Parte 1: classe anonima

Si osservi la classe Function, che modella una singola funzione con un solo input ed un solo output di tipo arbitrario.
Si implementi il metodo `identity()`, che deve restituire la funzione identià
(ossia, che restituisce l'input passato in ingresso senza modifica alcuna)
tramite una classe anonima.
Si osservi `TestFunctionalLibrary` per trovare esempi di classi anonime che implementano function.
Ci si prepari a rispondere alla seguente domanda al momento della correzione:
> perché `identity()` è un metodo, e non una costante `public static`?
Risposta: identity() è un metodo e non una costante public static perché così possiamo avere solamente un'interfaccia, e l'oggetto da ritornare tramite la funzione identità lo costruiamo lì dentro con una classe anonima che fa override di call, senza classi di appoggio. Con una costante avrei dovuto comunque implementare l'interfaccia con una classe anonima, ma il suo tipo sarebbe stato congelato alla dichiarazione — un campo static non può essere generico — quindi Function<Object, Object> non sarebbe stata assegnabile a una Function<String, String>, e avrei dovuto fare cast e sopprimere i warning. Il metodo invece ha il proprio <T>, dedotto a ogni chiamata: l'identità non esiste per un tipo solo, e una costante mi costringerebbe a scriverne una per ogni tipo.

## Parte 2: sfruttare le classi anonime per costruire una libreria funzionale

Si implementino le funzioni di utilità non ancora implementate all'interno di `Transformers`.
Queste funzioni rappresentano manipolazioni di tipo *funzionale* di collezioni.
Si leggano con attenzione i commenti Javadoc presenti per trovare la soluzione più compatta per implementare le funzioni
richieste.
Si minimizzino le duplicazioni di codice, e non si utilizzino metodi "di appoggio".

## Parte 3: classi innestate ed enum

All'interno della classe MonthSorterNested, si crei una `enum Month` che modella i mesi dell'anno.
Si suggerisce di valutare l'utilizzo di un campo che modella il numero di giorni del mese.
Questa enum *deve* avere un metodo `Month fromString(String)` che, data una stringa di testo, restituisce il `Month`
che meglio la rappresenta. A tal proposito, si legga con molta attenzione la Javadoc di `MonthSorter`.

Utilizzare questa `enum` come supporto per la costruzione di due classi innestate: `SortByMonthOrder` e `SortByDate`
che implementano `Comparator<String>` e rappresentano, rispettivamente, un comparatore che ordina delle stringhe
(interpretandole come mesi) in base al loro ordine nell'anno, ed un comparatore che le ordina invece in base al numero
di giorni che il mese ha.
