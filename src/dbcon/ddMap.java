package dbcon;

public interface ddMap {
    @select(sentence = "select * from dd")
    public void getDD();

    @select(sentence ="select * from prep")
    public void getPrep();

    @select(sentence = "select * from adminlog")
    public void getAdmin();
}
