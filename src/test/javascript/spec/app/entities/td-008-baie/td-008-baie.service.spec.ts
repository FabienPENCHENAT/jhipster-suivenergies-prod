import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TD008BaieService } from 'app/entities/td-008-baie/td-008-baie.service';
import { ITD008Baie, TD008Baie } from 'app/shared/model/td-008-baie.model';

describe('Service Tests', () => {
  describe('TD008Baie Service', () => {
    let injector: TestBed;
    let service: TD008BaieService;
    let httpMock: HttpTestingController;
    let elemDefault: ITD008Baie;
    let expectedResult: ITD008Baie | ITD008Baie[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TD008BaieService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new TD008Baie(0, 'AAAAAAA', 'AAAAAAA', 0, 0, 0, 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TD008Baie', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TD008Baie()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TD008Baie', () => {
        const returnedFromService = Object.assign(
          {
            reference: 'BBBBBB',
            td008Baie: 'BBBBBB',
            deperdition: 1,
            tv009CoefficientTransmissionThermiqueVitrage: 1,
            tv012CoefTransmissionThermiqueBaieProtectionSolaire: 1,
            surface: 1,
            perimetre: 1,
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

      it('should return a list of TD008Baie', () => {
        const returnedFromService = Object.assign(
          {
            reference: 'BBBBBB',
            td008Baie: 'BBBBBB',
            deperdition: 1,
            tv009CoefficientTransmissionThermiqueVitrage: 1,
            tv012CoefTransmissionThermiqueBaieProtectionSolaire: 1,
            surface: 1,
            perimetre: 1,
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

      it('should delete a TD008Baie', () => {
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
