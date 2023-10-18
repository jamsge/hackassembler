class Main
{
    public static void main(String []args)
    {
        Parser myParser = new Parser("test.txt");
        while(myParser.hasMoreLines()){
            System.out.println(myParser.advance());
            System.out.println(myParser.instructionType());
            System.out.println(myParser.dest());
            System.out.println(myParser.comp());
            System.out.println(myParser.jump());
            System.out.println(myParser.symbol());

            System.out.println();
        }
    }
};