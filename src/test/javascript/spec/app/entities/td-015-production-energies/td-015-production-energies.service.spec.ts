import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TD015ProductionEnergiesService } from 'app/entities/td-015-production-energies/td-015-production-energies.service';
import { ITD015ProductionEnergies, TD015ProductionEnergies } from 'app/shared/model/td-015-production-energies.model';

describe('Service Tests', () => {
  describe('TD015ProductionEnergies Service', () => {
    let injector: TestBed;
    let service: TD015ProductionEnergiesService;
    let httpMock: HttpTestingController;
    let elemDefault: ITD015ProductionEnergies;
    let expectedResult: ITD015ProductionEnergies | ITD015ProductionEnergies[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TD015ProductionEnergiesService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new TD015ProductionEnergies(0, 'AAAAAAA', 0, false, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TD015ProductionEnergies', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TD015ProductionEnergies()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TD015ProductionEnergies', () => {
        const returnedFromService = Object.assign(
          {
            tr004TypeEnergie: 'BBBBBB',
            productionElectriciteCapteursPhotovoltaiques: 1,
            productionleEctriciteMicroEolienne: true,
            productionCogeneration: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TD015ProductionEnergies', () => {
        const returnedFromService = Object.assign(
          {
            tr004TypeEnergie: 'BBBBBB',
            productionElectriciteCapteursPhotovoltaiques: 1,
            productionleEctriciteMicroEolienne: true,
            productionCogeneration: 1,
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

      it('should delete a TD015ProductionEnergies', () => {
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
