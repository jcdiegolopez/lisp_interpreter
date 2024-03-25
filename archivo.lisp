(defun fibonacci (n)
  (cond
    ((EQUAL n 0) 0)
    ((EQUAL n 1) 1)
    (t (+ (fibonacci (- n 1))
          (fibonacci (- n 2))))))
(fibonacci 10)