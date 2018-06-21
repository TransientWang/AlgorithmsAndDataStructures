package dbcon;

public interface ddMap {
    @select(sentence = "select * from sale")
    public void getDD();

    @select(sentence = "SELECT " +
            "COALESCE (zt, 'totals'), " +
            "COUNT(zt) AS 'total' " +
            "FROM " +
            "dd " +
            "GROUP BY " +
            "zt WITH ROLLUP " +
            "HAVING " +
            "total > 1")
    public void getPrep();

    @select(sentence = "select * from adminlog")
    public void getAdmin();
}
