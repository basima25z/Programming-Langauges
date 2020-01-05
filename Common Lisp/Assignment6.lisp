(defun myList()
    (list 4 '(7 22) '"art" '("math"  (8) 99 ) 100) ; creates a list, using a single quote instead of a double quote makes the strings lowercase instead of upper 
)

(defun leapYear()
(loop for x from 1800 to 2019 ;a loop from the start year of 1800 to 2019, x is the variable iterating through the years 
if (and (zerop (mod x 4 )) ;if selection statement that states if the year mod 4 is equal to zero AND if the year mod 100 is not equal to zero OR the year mod 400 is year to zero it collects the year and adds it a list 
(or (not (zerop (mod x 100))) (zerop (mod x 400)))) collect x ; collect x collects the year that is true by the if statement 
)
)



(defun union- (list1 list2)
(if (endp list1) ; endp used to recognize an empty list, if list1 is empty return list2
        list2
    (union- (rest list1) ;rest takes the rest of list1 and calls union again
        (if (member (first list1)list2) ; member searches the list for an item that matches, if first element is in list 2 then dont add it 
        list2 
        (push (first list1)list2) ; pushed the first value that matches into the second list 
        )
    )
)
)



(defun avg (list1 &optional (total 0) (totalLength 0))
  (if (endp list1) ; endp is used to recognize an empty list, if the list is empty it goes to the true block
     (/ total totalLength) ;true block, if the list is empty it divdes the total by totalLength, if nothing is sent in both total and totallength is set to 0
     (avg (rest list1) (+ total (first list1)) (+ totalLength 1)) ; tail recursion is demonstrated by avg calling itself again, it send the end of list1, the first of list1 added to total and 1 added to total length as paramters
   )
 )

(defun isType (dataType)
( lambda(x) ; lamda is the annoymous function, it allows one to create a function that return the function, lambda here simply returns the function
    (if (typep x dataType) ; if statement, typep returns true if the object is type datatype, else it returns NIL
        T
        NIL
    )
)
)



(defun taxCalculator (limit rate values)
    (loop for x in values collect ; loops through the values, x being the current value that it is iterating through
      (if (> x limit) ; if statement, the condition being if x is greater than limit
         (* x rate); if the condition is true, it multiplies the x (current value) times the rate and add its to the list
         x ; if the condition is false, it add x (current value) to the list as is 
      )   
    )   
)



(defmacro threeWayBranch (x y toExecute)
    `(if (< ,x ,y) ; if statement, if x < y then return the first object in the list, the comma signifies that you want the value of x and y and not the symbol
        (eval (car toExecute)) ; the backquote disables evaluation of every expression not preceded by the a comma, in this case everything that follows the comma operator is evaluated 
        ; eval is used to expand macrocalls to get a result
        ; car returns the first thing in toExecute 
    
    )
    `(if (> ,x ,y) ; if statement, if x > y then return the middle of the list, this is accomplish by first taking everything but the first thing to the list (by cdr) and then taking that list and only pulling out the first thing (by car)
        (eval  (car (cdr (toExecute)))) ; gets the middle value of the list
    )
    `(if (= ,x ,y) ; if statement, if x = y, it first takes everything but the first value in the list (cdr) and then it cdr's it again to get the first thing out of the second derivation of the list , so then it cars and returns the only value in the list that is left which is also the first
        (eval ((car (cdr (cdr (toExecute))))))
    )
)





(defun clean (fn values) 
     (remove nil ;bc within this loop, it just creating a list of all things, removes all the nils after the list is formulated
       (loop for x in values collect ; this loops through the values and collect to add to the list based off the if statment
         (if (listp x) ; if returns true if x is a list 
             (clean fn x) ; cleans it by removing all non-ints, cleans the sublists 
             (if (funcall fn x) ; applies functions to arguments 
                 x ; returns x if it does not need to be cleaned 
             )
         )
       )
     )
)



