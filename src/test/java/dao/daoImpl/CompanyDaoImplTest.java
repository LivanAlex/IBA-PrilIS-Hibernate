package dao.daoImpl;

import dao.CompanyDao;
import entity.Company;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompanyDaoImplTest {

    private CompanyDao companyDao = new CompanyDaoImpl();
    private static Company company;

    @BeforeAll
    static void init() {
        String companyName = "name";
        company = new Company();
        company.setCompanyName(companyName);
        company.setCompanyCountry("Georgia");
    }


    @Test
    void itShouldAddCompanyToDB() {
        //When
        companyDao.addCompany(company);
        //Then
        final Company expected = companyDao.findCompanyByName(company.getCompanyName());
        assertEquals(expected, company);
    }


    @Test
    void itShouldThrowsException() {
        //Then
        ConstraintViolationException thrown = assertThrows(
                ConstraintViolationException.class,
                () -> companyDao.addCompany(company)
        );
        assertEquals("could not execute statement", thrown.getMessage());
    }

    @Test
    void itShouldFindCompanyById() {
        //Given
        int id = company.getCompanyId();
        //When
        final Company companyFromDB = companyDao.findCompanyById(id);
        //Then
        assertEquals(company, companyFromDB);
    }

    @Test
    void itShouldFindCompanyByName() {
        //Given
        final String companyName = company.getCompanyName();
        //When
        final Company companyFromDB = companyDao.findCompanyByName(companyName);
        //Then
        assertEquals(company, companyFromDB);
    }

    @Test
    void itShouldReturnListOfCompanies(){
        //When
        final List<Company> companies = companyDao.showCompanies();
        assertEquals(company, companies.get(0));
    }

    @Test
    void itShouldUpdateCompany(){
        //Given
        int id = company.getCompanyId();
        Company companyFromDB = companyDao.findCompanyById(id);
        company.setCompanyCountry("USA");
        //When
        companyDao.updateCompany(company);
        //Then
        assertNotEquals(companyFromDB.getCompanyCountry(), company.getCompanyCountry());
        companyFromDB = companyDao.findCompanyById(id);
        assertEquals(companyFromDB.getCompanyCountry(), company.getCompanyCountry());
    }


    @Test
    void itShouldDeleteCompanyFromDB() {
        //Given
        int id = company.getCompanyId();
        //When
        final boolean isDeleted = companyDao.deleteCompany(id);
        //Then
        assertTrue(isDeleted);
    }
}