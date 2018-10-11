public class Program{
    public static void main(String[] args){
        Clock clock = new Clock();

        while(true){
            clock.Tick();
            System.out.println(clock.getOutputTime());
            try {
                Thread.sleep(1000); //1000 milliseconds is one second.
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class Clock
{
    private Counter _hour;
    private Counter _min;
    private Counter _sec;

    public Clock()
    {
        _hour = new Counter("hour");
        _min = new Counter("min");
        _sec = new Counter("sec");
    }

    public void Tick()
    {
        _sec.Increment();
        //System.out.println("====TEST====");
        if (_sec.getValue() == "60")
        {
            _min.Increment();
            _sec.Reset();

            if (_min.getValue() == "60")
            {
                _hour.Increment();
                _min.Reset();
            }
        }
    }

    public String getOutputTime()
    {
        String time = _hour.getValue() + ":" + _min.getValue() + ":" + _sec.getValue();
        return time;
    }

    public void ResetClock()
    {
        _hour.Reset();
        _min.Reset();
        _sec.Reset();
    }
}

class Counter
{
    private int _count;
    private String _name;

    public Counter(String name)
    {
        _name = name;
        _count = 0;
    }

    public void setCount(int count)
    {
        _count = count;
    }

    public String getName()
    {
        return _name;
    }

    public void setName(String name)
    {
        _name = name;
    }

    public String getValue()
    {
        String intToString = Integer.toString(_count);
        return intToString;
    }

    public int Increment()
    {
        return _count++;
    }

    public int Reset()
    {
        return _count = 0;
    }
}