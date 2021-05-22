package egovframework.platform.module.kobis.vo;

public class MovieVO {
	public String movieCd;
	public String movieNm;
	public String movieNmEn;
	public String prdtYear;
	public String openDt;
	public String typeNm;
	public String prdtStatNm;
	public String nationAlt;
	public String genreAlt;
	public String repNationNm;
	public String repGenreNm;
	public DirectorVO[] directors;
	public CompanyVO[] companys;

	public MovieVO() {
		super();
	}
}