import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TD002ConsommationsService } from 'app/entities/td-002-consommations/td-002-consommations.service';
import { ITD002Consommations, TD002Consommations } from 'app/shared/model/td-002-consommations.model';

describe('Service Tests', () => {
  describe('TD002Consommations Service', () => {
    let injector: TestBed;
    let service: TD002ConsommationsService;
    let httpMock: HttpTestingController;
    let elemDefault: ITD002Consommations;
    let expectedResult: ITD002Consommations | ITD002Consommations[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TD002ConsommationsService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new TD002Consommations(0, 'AAAAAAA', 'AAAAAAA', 0, 0, 0, 0, false);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TD002Consommations', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TD002Consommations()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TD002Consommations', () => {
        const returnedFromService = Object.assign(
          {
            tr006TypeUsage: 'BBBBBB',
            tr004TypEnergie: 'BBBBBB',
            tv042TarifEnergie: 1,
            consommationEnergieFinale: 1,
            consommationEnergiePrimaire: 1,
            fraisAnnuelsEnergie: 1,
            estEfface: true,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TD002Consommations', () => {
        const returnedFromService = Object.assign(
          {
            tr006TypeUsage: 'BBBBBB',
            tr004TypEnergie: 'BBBBBB',
            tv042TarifEnergie: 1,
            consommationEnergieFinale: 1,
            consommationEnergiePrimaire: 1,
            fraisAnnuelsEnergie: 1,
            estEfface: true,
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

      it('should delete a TD002Consommations', () => {
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
