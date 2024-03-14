import java.util.ArrayList;
import java.util.List;

public class ListN extends Expression {
    private List<Expression> elements;

    public ListN(List<Expression> elements) {
        this.elements = elements;
    }

    public List<Expression> getElements(){
        return this.elements;
    }

    @Override
    public Object evaluate(Environment environment) {
        // Evaluar cada elemento de la lista y devolver una nueva lista con los resultados
        System.out.println("Evaluando nashe");
        System.out.println(this.elements);
        System.out.println(environment.getVariables());
        System.out.println(environment.getFunctions());
        System.out.println(environment.getSymbols());
        System.out.println("no nashe");

        //Operaciones aritmeticas
        List<Object> result = new ArrayList<>(); //[funcion, n1, n2, n3, funcion2]
        System.out.println("result antes");
        System.out.println(result);

        for (Expression element : elements) {
            
            result.add(element.evaluate(environment));
        }

        System.out.println("result despues");
        System.out.println(result);

        List<Object> resultadosFinales = new ArrayList<>();
        //Separar llamadas de funciones aritmeticas
        int it = 0;
        List<Object> arguments;
        Function functionToCall;
        while (it < result.size()){
            if (result.get(it) instanceof Function) {
                functionToCall = (Function) result.get(it);
                arguments = new ArrayList<>();
                it += 1;

                int i;
                for (i = it; i < result.size(); i++) {
                    if (result.get(i) instanceof Number) {
                        arguments.add(result.get(i));
                    }
                }
                it += i;
                //Pasar argumentos a la función
                System.out.println("arg");
                System.out.println(arguments);
                System.out.println("arg");
                resultadosFinales.add(functionToCall.call(environment, arguments));
                continue;
            } else if (result.get(it) instanceof Expression){

            }
            it += 1;
        }
        return resultadosFinales.get(0);

        //Resto de simbolos
        /*List<Object> result = new ArrayList<>();
        for (Expression element : elements) {
            result.add(element.evaluate(environment));
        }
        return result;*/
    }
}
