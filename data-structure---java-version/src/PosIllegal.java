public class PosIllegal extends RuntimeException {
    public PosIllegal() {
    }
    public PosIllegal(String msg)
    {
      super(msg);
    }
}
