class task1{
    public static void main(String[] args){
        Rectangle rec = new Rectangle();
        rec.draw();
    }
}    
    abstract class Shap{
        Shap(){
            System.out.println("Parent constructor");
        }
        abstract void draw();

    }
    class Rectangle extends Shap{
        Rectangle(){
            System.out.println("Rectangle constructor");
        }
        void draw(){
            System.out.println("rectangle draw");
        }
    }
    class Circle extends Shap{
        void draw(){
            System.out.println("Circle draw");
        }
    }
