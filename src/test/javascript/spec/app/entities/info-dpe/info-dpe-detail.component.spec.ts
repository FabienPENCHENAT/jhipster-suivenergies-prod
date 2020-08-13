import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { SuivEnergiesTestModule } from '../../../test.module';
import { InfoDPEDetailComponent } from 'app/entities/info-dpe/info-dpe-detail.component';
import { InfoDPE } from 'app/shared/model/info-dpe.model';

describe('Component Tests', () => {
  describe('InfoDPE Management Detail Component', () => {
    let comp: InfoDPEDetailComponent;
    let fixture: ComponentFixture<InfoDPEDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ infoDPE: new InfoDPE(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [InfoDPEDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(InfoDPEDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(InfoDPEDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load infoDPE on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.infoDPE).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });

    describe('byteSize', () => {
      it('Should call byteSize from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'byteSize');
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.byteSize(fakeBase64);

        // THEN
        expect(dataUtils.byteSize).toBeCalledWith(fakeBase64);
      });
    });

    describe('openFile', () => {
      it('Should call openFile from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'openFile');
        const fakeContentType = 'fake content type';
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.openFile(fakeContentType, fakeBase64);

        // THEN
        expect(dataUtils.openFile).toBeCalledWith(fakeContentType, fakeBase64);
      });
    });
  });
});
