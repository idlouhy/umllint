package net.umllint.tool.cli;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public class TextUIProgressbar implements IPercentUpdateListener {

    private Float percent = new Float(0.0);
    private int charSize = 80;
    private int drawn = 0;

    private String subject;


    public TextUIProgressbar(String subject) {
        this.subject = subject;
    }


    public void render() {
        System.out.print(String.format(subject));

        int subjectLength = subject.length();
        int spacing = charSize-1-subjectLength;

        for (int i=0; i<spacing; i++) {
            System.out.print(" ");
        }

        System.out.print("|");
        System.out.println();
    }


    @Override
    public void setPercent(Float percent) {
        this.percent = percent;
        redraw();
    }

    public void redraw() {

        int size = (int) (charSize*percent);
        int draw = size - drawn;

        for (int i=0; i<draw; i++) {
            System.out.print("#");
        }

        drawn = size;
    }



}



