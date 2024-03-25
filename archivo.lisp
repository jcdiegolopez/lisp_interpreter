(defun counter (n)
  (cond ((> n 10) n)
        (t (counter(+ n 1)))))
(counter 0)