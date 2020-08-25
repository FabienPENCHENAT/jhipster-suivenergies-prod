import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TD010PontsThermiquesService } from 'app/entities/td-010-ponts-thermiques/td-010-ponts-thermiques.service';
import { ITD010PontsThermiques, TD010PontsThermiques } from 'app/shared/model/td-010-ponts-thermiques.model';

describe('Service Tests', () => {
  describe('TD010PontsThermiques Service', () => {
    let injector: TestBed;
    let service: TD010PontsThermiquesService;
    let httpMock: HttpTestingController;
    let elemDefault: ITD010PontsThermiques;
    let expectedResult: ITD010PontsThermiques | ITD010PontsThermiques[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TD010PontsThermiquesService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new TD010PontsThermiques(0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TD010PontsThermiques', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TD010PontsThermiques()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TD010PontsThermiques', () => {
        const returnedFromService = Object.assign(
          {
            longueur: 1,
            tv013ValeurPontThermique: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TD010PontsThermiques', () => {
        const returnedFromService = Object.assign(
          {
            longueur: 1,
            tv013ValeurPontThermique: 1,
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

      it('should delete a TD010PontsThermiques', () => {
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
