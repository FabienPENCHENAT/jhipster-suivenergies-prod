import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TD011InstalationChauffageService } from 'app/entities/td-011-instalation-chauffage/td-011-instalation-chauffage.service';
import { ITD011InstalationChauffage, TD011InstalationChauffage } from 'app/shared/model/td-011-instalation-chauffage.model';

describe('Service Tests', () => {
  describe('TD011InstalationChauffage Service', () => {
    let injector: TestBed;
    let service: TD011InstalationChauffageService;
    let httpMock: HttpTestingController;
    let elemDefault: ITD011InstalationChauffage;
    let expectedResult: ITD011InstalationChauffage | ITD011InstalationChauffage[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TD011InstalationChauffageService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new TD011InstalationChauffage(0, 'AAAAAAA', 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TD011InstalationChauffage', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TD011InstalationChauffage()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TD011InstalationChauffage', () => {
        const returnedFromService = Object.assign(
          {
            tr003TypeInstallationChauffage: 'BBBBBB',
            surfaceChauffee: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TD011InstalationChauffage', () => {
        const returnedFromService = Object.assign(
          {
            tr003TypeInstallationChauffage: 'BBBBBB',
            surfaceChauffee: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a TD011InstalationChauffage', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
