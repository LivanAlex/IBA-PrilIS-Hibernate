package dao.daoImpl;

import entity.Company;
import entity.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompanyDaoImplTest {

    CompanyDaoImpl companyDao = new CompanyDaoImpl();

    @Test
    void findCompanyById() {
        Company companyById = companyDao.findCompanyById(4);
        System.out.println(companyById);
    }

    @Test
    void findCompanyByName() {
        Company companyById = companyDao.findCompanyByName("qwe");
        System.out.println(companyById);
    }
}